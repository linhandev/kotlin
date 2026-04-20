/*
 * Copyright (c) 2026 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#pragma once

#include <limits>
#include <optional>

#include "KString.h"

// NOTE: All declarations and template definitions in this file are copied from
// kotlin-native/runtime/src/main/cpp/KString.cpp; please keep them consistent
// with the original file.
namespace hmm::string {
    // template parameter F has signature R(StringData<*>)
    template <typename F>
    auto encodingAware(KConstRef string, F&& impl) {
        auto header = StringHeader::of(string);
        switch (header->encoding()) {
            case StringEncoding::kUTF16:
                return impl(StringData<StringEncoding::kUTF16>(header));
            case StringEncoding::kLatin1:
                return impl(StringData<StringEncoding::kLatin1>(header));
        }
    }

    template <typename It1, typename It2>
    inline KInt Kotlin_String_compareAt(It1 it1, It1 end1, It2 it2, It2 end2) {
        if (it1 == end1 && it2 == end2) {
            return 0;
        }
        if (it1 == end1) {
            return -1;
        }
        if (it2 == end2) {
            return 1;
        }
        KChar c1 = *it1;
        KChar c2 = *it2;
        if (c1 == c2) {
            // Assuming the iterators were produced by std::mismatch, this is only possible
            // when searching in raw memory then rolling back to the previous unit in non-UTF-16
            // encodings. In this case this must be a surrogate pair where the first element is
            // equal, but the second element is not.
            c1 = *++it1;
            c2 = *++it2;
        }
        return c1 < c2 ? -1 : 1;
    }

    template <typename String, typename It>
    bool isInSurrogatePair(String&& string, It&& it) {
        return string.at(it.ptr()) != it;
    }

    std::optional<KInt> Kotlin_String_cachedHashCode(KConstRef thiz);

    template <KStringConversionMode mode>
    std::string to_string_impl(const KChar* it, const KChar* end)
            noexcept(mode != KStringConversionMode::CHECKED);

    template <KStringConversionMode>
    std::string to_string_impl(const uint8_t* it, const uint8_t* end) noexcept;

    inline constexpr uint32_t MAX_STRING_SIZE =
        static_cast<uint32_t>(std::numeric_limits<int32_t>::max());
}
