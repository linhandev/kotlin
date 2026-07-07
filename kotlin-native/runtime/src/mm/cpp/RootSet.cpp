/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "RootSet.hpp"

#include "KAssert.h"
#include "GlobalData.hpp"
#include "ThreadData.hpp"

// The previous in-source `#define ENABLE_STACKMAP 1` has been removed. The macro
// is now driven by the build script via `-DENABLE_STACKMAP=1`
// (see `kotlin-native/runtime/build.gradle.kts::enableStackmap`). The mm module
// declares `if (enableStackmap) compilerArgs.add("-DENABLE_STACKMAP=1")`, so this
// file still resolves the same way under default ON builds.

using namespace kotlin;

mm::ThreadRootSet::Iterator::Iterator(begin_t, ThreadRootSet& owner) noexcept
    : owner_(owner), phase_(Phase::kStack), stackIterator_(owner_.stack_.begin())
    {
    Init();
}

mm::ThreadRootSet::Iterator::Iterator(end_t, ThreadRootSet& owner) noexcept : owner_(owner), phase_(Phase::kDone) {}

mm::ThreadRootSet::Value mm::ThreadRootSet::Iterator::operator*() noexcept
{
    switch (phase_) {
        case Phase::kStack:
            return {*stackIterator_, Source::kStack};
        case Phase::kTLS:
            return {**tlsIterator_, Source::kTLS};
        case Phase::kHandle:
            return {*reinterpret_cast<ObjHeader**>(*kHandleIterator_), Source::kHandle};
        case Phase::kDone:
            RuntimeFail("Cannot dereference");
    }
}

mm::ThreadRootSet::Iterator& mm::ThreadRootSet::Iterator::operator++() noexcept
{
    switch (phase_) {
        case Phase::kStack:
            ++stackIterator_;
            Init();
            return *this;
        case Phase::kTLS:
            ++tlsIterator_;
            Init();
            return *this;
        case Phase::kHandle:
            ++kHandleIterator_;
            Init();
            return *this;
        case Phase::kDone:
            return *this;
    }
}

bool mm::ThreadRootSet::Iterator::operator==(const Iterator& rhs) const noexcept
{
    if (phase_ != rhs.phase_) {
        return false;
    }

    switch (phase_) {
        case Phase::kDone:
            return true;
        case Phase::kStack:
            return stackIterator_ == rhs.stackIterator_;
        case Phase::kTLS:
            return tlsIterator_ == rhs.tlsIterator_;
        case Phase::kHandle:
            return kHandleIterator_ == rhs.kHandleIterator_;
    }
}

void mm::ThreadRootSet::Iterator::Init() noexcept
{
    while (phase_ != Phase::kDone) {
        switch (phase_) {
            case Phase::kStack:
                if (stackIterator_ != owner_.stack_.end()) return;
                phase_ = Phase::kTLS;
                tlsIterator_ = owner_.tls_.begin();
                break;
            case Phase::kTLS:
                if (tlsIterator_ != owner_.tls_.end()) return;
                phase_ = Phase::kHandle;
                kHandleIterator_ = owner_.handleScopeData_.begin();
                break;
            case Phase::kHandle:
                if (kHandleIterator_ != owner_.handleScopeData_.end()) {
                    return;
                }
                phase_ = Phase::kDone;
                break;
            case Phase::kDone:
                RuntimeFail("Impossible");
        }
    }
}

mm::GlobalRootSet::Iterator::Iterator(begin_t, GlobalRootSet& owner) noexcept
    : owner_(owner), phase_(Phase::kGlobals), globalsIterator_(owner_.globalsIterable_.begin())
    {
    Init();
}

mm::GlobalRootSet::Iterator::Iterator(end_t, GlobalRootSet& owner) noexcept : owner_(owner), phase_(Phase::kDone) {}

mm::GlobalRootSet::Value mm::GlobalRootSet::Iterator::operator*() noexcept
{
    switch (phase_) {
        case Phase::kGlobals:
            return {**globalsIterator_, Source::kGlobal};
        case Phase::kStableRefs:
            return {*externalRCRefsIterator_, Source::kStableRef};
        case Phase::kDone:
            RuntimeFail("Cannot dereference");
    }
}

mm::GlobalRootSet::Iterator& mm::GlobalRootSet::Iterator::operator++() noexcept
{
    switch (phase_) {
        case Phase::kGlobals:
            ++globalsIterator_;
            Init();
            return *this;
        case Phase::kStableRefs:
            ++externalRCRefsIterator_;
            Init();
            return *this;
        case Phase::kDone:
            return *this;
    }
}

bool mm::GlobalRootSet::Iterator::operator==(const Iterator& rhs) const noexcept
{
    if (phase_ != rhs.phase_) {
        return false;
    }

    switch (phase_) {
        case Phase::kDone:
            return true;
        case Phase::kGlobals:
            return globalsIterator_ == rhs.globalsIterator_;
        case Phase::kStableRefs:
            return externalRCRefsIterator_ == rhs.externalRCRefsIterator_;
    }
}

void mm::GlobalRootSet::Iterator::Init() noexcept
{
    while (phase_ != Phase::kDone) {
        switch (phase_) {
            case Phase::kGlobals:
                if (globalsIterator_ != owner_.globalsIterable_.end()) return;
                phase_ = Phase::kStableRefs;
                externalRCRefsIterator_ = owner_.externalRCRefsIterable_.begin();
                break;
            case Phase::kStableRefs:
                if (externalRCRefsIterator_ != owner_.externalRCRefsIterable_.end()) return;
                phase_ = Phase::kDone;
                break;
            case Phase::kDone:
                RuntimeFail("Impossible");
        }
    }
}

mm::ThreadRootSet::ThreadRootSet(ThreadData& threadData) noexcept
    : ThreadRootSet(
                threadData.shadowStack(),
                threadData.tls(),
                threadData.GetHandleScopeData()) {}

mm::GlobalRootSet::GlobalRootSet() noexcept
    : GlobalRootSet(mm::GlobalData::Instance().globalsRegistry(), mm::GlobalData::Instance().externalRCRefRegistry()) {}
