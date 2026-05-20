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

#ifdef KONAN_OHOS

#include <cstdio>
#include <cstdlib>
#include <limits>
#include <string>
#include <string.h>
#include <codecvt>
#include <locale>

#include <napi/native_api.h>

#include "ArkTSStringRef.h"
#include "KAssert.h"
#include "Exceptions.h"
#include "Natives.h"
#include "KString.h"
#include "KStringProxyOHOS.h"
#include "Porting.h"
#include "utf8.h"
#include "polyhash/PolyHash.h"
// Ported from huxiaowen 520f37e5c "FFI: add pin and objholder": EnterPinScope
// keeps Kotlin string objects from being moved by CRT's compacting GC while
// native code holds raw pointers into their data (via the proxy or via
// CreateUninitializedString).
#include "PinScope.h"

namespace hmm {
using namespace hmm::string;

ALWAYS_INLINE ArkTSStringRef* KStringProxyGetArkTSStringRef(KConstRef proxy) {
    return static_cast<ArkTSStringRef*>(proxy->GetAssociatedObject());
}

OBJ_GETTER(Kotlin_ArkTS_CreateStringByCopy, napi_env env, napi_value value) {
    size_t length = 0;
    {
        kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
        auto status = napi_get_value_string_utf16(env, value, nullptr, 0, &length);
        if (status != napi_ok) {
            RuntimeLogError({ kotlin::logging::Tag::kRT },
                            "Kotlin_ArkTS_CreateStringByCopy get string length failed, status = %d",
                            status);
        }
    }
    if (length == 0) {
        // return an empty string
        RETURN_RESULT_OF0(TheEmptyString);
    }

    KRef result = CreateUninitializedString(StringEncoding::kUTF16, length, OBJ_RESULT);
    kotlin::EnterPinScope<void*> scope((void*)result);
    {
        kotlin::ThreadStateGuard guard(kotlin::ThreadState::kNative, true);
        auto status = napi_get_value_string_utf16(
            env, value,
            reinterpret_cast<char16_t*>(StringHeader::of(result)->data()),
            length + 1, nullptr);
        if (status != napi_ok) {
            RuntimeLogError({ kotlin::logging::Tag::kRT },
                            "Kotlin_ArkTS_CreateStringByCopy get string value failed, status = %d",
                            status);
            }
    }
    RETURN_OBJ(result);
}

OBJ_GETTER(Kotlin_ArkTS_CreateStringByProxy, ArkTSStringRef* ref) {
    // Create a minimal Kotlin String instance (UTF-16 encoding, length from proxy).
    auto proxyLength = static_cast<uint32_t>(ref->getLength());
    KRef result = CreateUninitializedString(StringEncoding::kUTF16, proxyLength, OBJ_RESULT);
    // Mark this Kotlin String as a proxy.
    StringHeader::of(result)->flags_ |= StringHeader::KSTRING_IS_PROXY;
    // Associate the ArkTS String proxy.
    result->SetAssociatedObject((void *)ref);
    RETURN_OBJ(result);
}

ALWAYS_INLINE bool IsKStringProxy(KConstRef string) {
    return StringHeader::of(string)->flags_ & StringHeader::KSTRING_IS_PROXY;
}

ALWAYS_INLINE OBJ_GETTER(unsafeUtf16ToUtf8Impl, KConstRef thizProxy, KInt start, KInt size, utf16to8 conversion) {
    ArkTSStringRef* ref = KStringProxyGetArkTSStringRef(thizProxy);
    std::string utf8;
    utf8.reserve(size);
    ref->withStringView([&](std::u16string_view sv) {
        const KChar* utf16 = reinterpret_cast<const KChar*>(sv.data() + start);
        conversion(utf16, utf16 + size, back_inserter(utf8));
    });
    ArrayHeader* result = AllocArrayInstance(theByteArrayTypeInfo, utf8.size(), OBJ_RESULT)->array();
    ::memcpy(ByteArrayAddressOfElementAt(result, 0), utf8.c_str(), utf8.size());
    RETURN_OBJ(result->obj());
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_replace, KConstRef thiz, KChar oldChar, KChar newChar) {
    ArkTSStringRef* ref = KStringProxyGetArkTSStringRef(thiz);
    if (ref->getLength() == 0) RETURN_RESULT_OF0(TheEmptyString);
    KRef result = CreateUninitializedString(StringEncoding::kUTF16, ref->getLength(), OBJ_RESULT);
    kotlin::EnterPinScope<void*> scope((void*)result);
    KChar *resultRaw = reinterpret_cast<KChar*>(StringHeader::of(result)->data());
    ref->withStringView([&](std::u16string_view sv) {
        for (char16_t c : sv) {
            KChar thizChar = (KChar)c;
            *resultRaw++ = thizChar == oldChar ? newChar : thizChar;
        }
    });
    RETURN_OBJ(result);
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_plusStringProxyImpl, KConstRef thizProxy, KConstRef otherProxy) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);

    auto thizLength = thizRef->getLength();
    auto otherLength = otherRef->getLength();

    RuntimeAssert(thizLength <= MAX_STRING_SIZE, "this cannot be this large");
    RuntimeAssert(otherLength <= MAX_STRING_SIZE, "this cannot be this large");
    size_t resultLength = thizLength + otherLength;
    if (resultLength > MAX_STRING_SIZE) {
        ThrowOutOfMemoryError();
    }

    // Create a Kotlin String instance with expected length
    KRef result = CreateUninitializedString(StringEncoding::kUTF16, resultLength, OBJ_RESULT);
    kotlin::EnterPinScope<void*> scope((void*)result);
    auto header = StringHeader::of(result);
    thizRef->copyTo(header->data(), thizLength * sizeof(KChar), 0);
    otherRef->copyTo(header->data() + thizLength * sizeof(KChar), otherLength * sizeof(KChar), 0);
    RETURN_OBJ(result);
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_plusStringImpl, KConstRef thizProxy, KConstRef other) {
    kotlin::EnterPinScope<void*> otherScope((void*)other);
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    auto thizLength = thizRef->getLength();
    return encodingAware(other, [=](auto other) {
        RuntimeAssert(thizLength <= MAX_STRING_SIZE, "this cannot be this large");
        RuntimeAssert(other.sizeInChars() <= MAX_STRING_SIZE, "this cannot be this large");
        size_t resultLength = thizLength + other.sizeInChars();
        if (resultLength > MAX_STRING_SIZE) {
            ThrowOutOfMemoryError();
        }
        KRef result = CreateUninitializedString(StringEncoding::kUTF16, resultLength, OBJ_RESULT);
        kotlin::EnterPinScope<void*> resScope((void*)result);
        KChar* out = reinterpret_cast<KChar*>(StringHeader::of(result)->data());
        thizRef->copyTo(out, thizLength * sizeof(KChar), 0);
        if constexpr (other.encoding == StringEncoding::kUTF16) {
            std::copy(other.begin().ptr(), other.end().ptr(), out + thizLength);
        } else {
            std::copy(other.begin(), other.end(), out + thizLength);
        }
        RETURN_OBJ(result);
    });
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_String_plusStringProxyImpl, KConstRef thiz, KConstRef otherProxy) {
    kotlin::EnterPinScope<void*> thizScope((void*)thiz);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
    auto otherLength = otherRef->getLength();
    return encodingAware(thiz, [=](auto thiz) {
        RuntimeAssert(thiz.sizeInChars() <= MAX_STRING_SIZE, "this cannot be this large");
        RuntimeAssert(otherLength <= MAX_STRING_SIZE, "this cannot be this large");
        size_t resultLength = thiz.sizeInChars() + otherLength;
        if (resultLength > MAX_STRING_SIZE) {
            ThrowOutOfMemoryError();
        }
        KRef result = CreateUninitializedString(StringEncoding::kUTF16, resultLength, OBJ_RESULT);
        kotlin::EnterPinScope<void*> resScope((void*)result);
        KChar* out = reinterpret_cast<KChar*>(StringHeader::of(result)->data());
        if (thiz.encoding == StringEncoding::kUTF16) {
            auto halfway = std::copy(thiz.begin().ptr(), thiz.end().ptr(), out);
            otherRef->copyTo(halfway, otherLength * sizeof(KChar), 0);
        } else {
            auto halfway = std::copy(thiz.begin(), thiz.end(), out);
            otherRef->copyTo(halfway, otherLength * sizeof(KChar), 0);
        }
        RETURN_OBJ(result);
    });
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_toCharArray,
                         KConstRef stringProxy,
                         KRef destination,
                         KInt destinationOffset,
                         KInt start,
                         KInt size) {
    ArkTSStringRef *stringRef = KStringProxyGetArkTSStringRef(stringProxy);
    ArrayHeader* destinationArray = destination->array();

    stringRef->copyTo(CharArrayAddressOfElementAt(destinationArray, destinationOffset),
                      size * sizeof(KChar), start);
    RETURN_OBJ(destinationArray->obj());
}

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_subSequence, KConstRef thizProxy, KInt startIndex, KInt endIndex) {
    ArkTSStringRef *ref = KStringProxyGetArkTSStringRef(thizProxy);
    if (startIndex < 0 || static_cast<size_t>(endIndex) > ref->getLength() || startIndex > endIndex) {
        ThrowArrayIndexOutOfBoundsException();
    }
    if (startIndex == endIndex) {
        RETURN_RESULT_OF0(TheEmptyString);
    }
    KInt length = endIndex - startIndex;
    KRef result = CreateUninitializedString(StringEncoding::kUTF16, length, OBJ_RESULT);
    kotlin::EnterPinScope<void*> scope((void*)result);
    ref->copyTo(StringHeader::of(result)->data(), length * sizeof(KChar), startIndex);
    RETURN_OBJ(result);
}

ALWAYS_INLINE KInt Kotlin_StringProxy_compareToStringProxy(KConstRef thizProxy, KConstRef otherProxy) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
    return thizRef->withStringView(otherRef, [&](std::u16string_view thizView, std::u16string_view otherView) {
        auto begin1 = &*thizView.begin();
        auto end1 = &*thizView.end();
        auto begin2 = &*otherView.begin();
        auto end2 = &*otherView.end();
        auto [ptr1, ptr2] = std::mismatch(begin1, end1, begin2, end2);
        return Kotlin_String_compareAt(ptr1, end1, ptr2, end2);
    });
}

ALWAYS_INLINE KInt Kotlin_StringProxy_compareToString(KConstRef thizProxy, KConstRef other) {
    kotlin::EnterPinScope<void*> otherScope((void*)other);
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    return encodingAware(other, [=](auto other) {
        return thizRef->withStringView([=](std::u16string_view thizView) {
            auto begin1 = thizView.begin();
            auto end1 = thizView.end();
            auto begin2 = other.begin();
            auto end2 = other.end();
            if constexpr (other.encoding == StringEncoding::kUTF16) {
                auto [ptr1, ptr2] = std::mismatch(&*begin1, &*end1, begin2.ptr(), end2.ptr());
                return Kotlin_String_compareAt(begin1 + (ptr1 - thizView.data()),
                                               end1, other.at(ptr2), end2);
            } else {
                auto [it1, it2] = std::mismatch(begin1, end1, begin2, end2);
                return Kotlin_String_compareAt(it1, end1, it2, end2);
            }
        });
    });
}

ALWAYS_INLINE KInt Kotlin_String_compareToStringProxy(KConstRef thiz, KConstRef otherProxy) {
    kotlin::EnterPinScope<void*> thizScope((void*)thiz);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
    return encodingAware(thiz, [=](auto thiz) {
        return otherRef->withStringView([=](std::u16string_view otherView) {
            auto begin1 = thiz.begin();
            auto end1 = thiz.end();
            auto begin2 = otherView.begin();
            auto end2 = otherView.end();
            if constexpr (thiz.encoding == StringEncoding::kUTF16) {
                auto [ptr1, ptr2] = std::mismatch(begin1.ptr(), end1.ptr(), &*begin2, &*end2);
                return Kotlin_String_compareAt(thiz.at(ptr1), end1,
                                               begin2 + (ptr2 - otherView.data()), end2);
            } else {
                auto [it1, it2] = std::mismatch(begin1, end1, begin2, end2);
                return Kotlin_String_compareAt(it1, end1, it2, end2);
            }
        });
    });
}

ALWAYS_INLINE KChar Kotlin_StringProxy_get(KConstRef thizProxy, KInt index) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    return static_cast<KChar>(thizRef->getChar(index));
}

ALWAYS_INLINE KBoolean Kotlin_StringProxy_equalsWithStringProxy(KConstRef thizProxy, KConstRef otherProxy) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
    if (thizRef == otherRef) {
        return true;
    }

    if (auto thizHash = thizRef->getHashCode()) {
        if (auto otherHash = otherRef->getHashCode()) {
            if (*thizHash != *otherHash) return false;
        }
    }

    return thizRef->withStringView(otherRef, [&](std::u16string_view thizView, std::u16string_view otherView) {
        return std::equal(&*thizView.begin(), &*thizView.end(),
                          &*otherView.begin(), &*otherView.end());
    });
}

ALWAYS_INLINE KBoolean Kotlin_StringProxy_equalsWithString(KConstRef thizProxy, KConstRef other) {
    kotlin::EnterPinScope<void*> otherScope((void*)other);
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);

    if (auto thizHash = thizRef->getHashCode()) {
        if (auto otherHash = string::Kotlin_String_cachedHashCode(other)) {
            if (*thizHash != *otherHash) return false;
        }
    }

    return encodingAware(other, [=](auto other) {
        return thizRef->withStringView([=](std::u16string_view thizView) {
            if constexpr (other.encoding == StringEncoding::kUTF16) {
                return std::equal(&*thizView.begin(), &*thizView.end(),
                                  other.begin().ptr(), other.end().ptr());
            } else {
                return std::equal(thizView.begin(), thizView.end(),
                                  other.begin(), other.end());
            }
        });
    });
}

ALWAYS_INLINE KBoolean Kotlin_StringProxy_unsafeRangeEqualsWithStringProxy(KConstRef thizProxy,
                                                                           KInt thizOffset,
                                                                           KConstRef otherProxy,
                                                                           KInt otherOffset,
                                                                           KInt length) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
    return thizRef->withStringView(otherRef, [&](std::u16string_view thizView, std::u16string_view otherView) {
        return memcmp(thizView.data() + thizOffset,
                      otherView.data() + otherOffset,
                      length * sizeof(KChar)) == 0;
    });
}

ALWAYS_INLINE KBoolean Kotlin_StringProxy_unsafeRangeEqualsWithString(KConstRef thizProxy,
                                                                      KInt thizProxyOffset,
                                                                      KConstRef other,
                                                                      KInt otherOffset,
                                                                      KInt length) {
    kotlin::EnterPinScope<void*> otherScope((void*)other);
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);

    return encodingAware(other, [=](auto other) {
        return thizRef->withStringView([=](std::u16string_view thizView) {
            auto begin1 = thizView.begin() + thizProxyOffset;
            auto begin2 = other.begin() + otherOffset;

            auto end1 = begin1 + length;
            auto end2 = begin2 + length;

            if constexpr (other.encoding == StringEncoding::kUTF16) {
                return std::equal(&*begin1, &*end1, begin2.ptr(), end2.ptr());
            } else {
                return std::equal(begin1, end1, begin2, end2);
            }
        });
    });
}

ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfChar(KConstRef thizProxy, KChar ch, KInt fromIndex) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    size_t pos = thizRef->withStringView([&](std::u16string_view sv) {
        return sv.find(static_cast<char16_t>(ch), static_cast<size_t>(fromIndex));
    });
    return (pos == std::u16string_view::npos) ? -1 : static_cast<KInt>(pos);
}

ALWAYS_INLINE KInt Kotlin_StringProxy_lastIndexOfChar(KConstRef thizProxy, KChar ch, KInt fromIndex) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    size_t pos = thizRef->withStringView([&](std::u16string_view thizView) {
        return thizView.rfind(static_cast<char16_t>(ch), static_cast<size_t>(fromIndex));
    });
    return (pos == std::u16string_view::npos) ? -1 : static_cast<KInt>(pos);
}

ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfStringProxy(KConstRef thizProxy, KConstRef otherProxy, KInt fromIndex) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);

    if (static_cast<size_t>(fromIndex) >= thizRef->getLength()
            || otherRef->getLength() > thizRef->getLength()) {
        return -1;
    }

    size_t pos = thizRef->withStringView(
        otherRef,
        [&](std::u16string_view thizView, std::u16string_view otherView) {
            return thizView.find(otherView, static_cast<size_t>(fromIndex));
        });
    return (pos == std::u16string_view::npos) ? -1 : static_cast<KInt>(pos);
}

ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfString(KConstRef thizProxy, KConstRef other, KInt fromIndex) {
    kotlin::EnterPinScope<void*> otherScope((void*)other);
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    return thizRef->withStringView([=](std::u16string_view thizView) {
        return encodingAware(other, [=](auto other) {
            auto thizLength = thizRef->getLength();
            auto otherLength = other.sizeInChars();
            if (static_cast<size_t>(fromIndex) >= thizLength) {
                return otherLength == 0 ? static_cast<KInt>(thizLength) : -1;
            }
            if (otherLength > thizLength) {
                return -1;
            }
            if (otherLength == 0) {
                return static_cast<KInt>(fromIndex);
            }
            if constexpr (other.encoding == StringEncoding::kUTF16) {
                auto patternStart = other.begin().ptr();
                auto patternEnd = other.end().ptr();
                auto start = thizView.begin() + fromIndex;
                auto end = thizView.end();
                auto shift = fromIndex;
                while (start != end) {
                    auto ptr = std::search(&*start, &*end, patternStart, patternEnd);
                    if (ptr == &*end) {
                        return -1;
                    }
                    auto it = thizView.begin() + (ptr - thizView.data());
                    if (ptr == &*it) {
                        return static_cast<KInt>(it - start + shift);
                    }
                    shift += it - start + 1;
                    start = ++it;
                }
                return -1;
            } else {
                auto start = thizView.begin() + fromIndex;
                auto end = thizView.end();
                auto it = std::search(start, end, other.begin(), other.end());
                return it == end ? -1 : static_cast<KInt>(it - start + fromIndex);
            }
        });
    });
}

ALWAYS_INLINE KInt Kotlin_String_indexOfStringProxy(KConstRef thiz, KConstRef otherProxy, KInt fromIndex) {
    kotlin::EnterPinScope<void*> thizScope((void*)thiz);
    return encodingAware(thiz, [=](auto thiz) {
        ArkTSStringRef *otherRef = KStringProxyGetArkTSStringRef(otherProxy);
        return otherRef->withStringView([&](std::u16string_view otherView) {
            auto thizLength = thiz.sizeInChars();
            auto otherLength = otherRef->getLength();
            if (static_cast<size_t>(fromIndex) >= thizLength) {
                return otherLength == 0 ? static_cast<KInt>(thizLength) : -1;
            }
            if (otherLength > thizLength) {
                return -1;
            }
            if (otherLength == 0) {
                return static_cast<KInt>(fromIndex);
            }
            if constexpr (thiz.encoding == StringEncoding::kUTF16) {
                auto patternStart = &*otherView.begin();
                auto patternEnd = &*otherView.end();
                auto start = thiz.begin() + fromIndex;
                auto end = thiz.end();
                auto shift = fromIndex;
                while (start != end) {
                    if (isInSurrogatePair(thiz, start)) {
                        ++start;
                        ++shift;
                    }
                    auto ptr = std::search(start.ptr(), end.ptr(), patternStart, patternEnd);
                    if (ptr == end.ptr()) {
                        return -1;
                    }
                    auto it = thiz.at(ptr);
                    if (ptr == it.ptr()) {
                        return static_cast<KInt>(it - start + shift);
                    }
                    shift += it - start + 1;
                    start = ++it;
                }
                return -1;
            } else {
                auto start = thiz.begin() + fromIndex;
                auto end = thiz.end();
                auto it = std::search(start, end, otherView.begin(), otherView.end());
                return it == end ? -1 : static_cast<KInt>(it - start + fromIndex);
            }
        });
    });
}

ALWAYS_INLINE KInt Kotlin_StringProxy_hashCode(KConstRef thizProxy) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    if (auto cached = thizRef->getHashCode()) {
        return *cached;
    }

    KInt result = thizRef->withStringView([&](std::u16string_view thizView) {
        const KChar* thizAddr = reinterpret_cast<const KChar*>(thizView.data());
        return polyHash(thizRef->getLength(), thizAddr);
    });
    if (result != 0) {
        thizRef->setHashCode(result);
    }
    return result;
}

ALWAYS_INLINE KNativePtr Kotlin_StringProxy_getStringAddressOfElement(KConstRef thizProxy, KInt index) {
    ArkTSStringRef *thizRef = KStringProxyGetArkTSStringRef(thizProxy);
    return reinterpret_cast<KNativePtr>(
        const_cast<char16_t*>(thizRef->getStringAddressOf(index)));
}

} // namespace hmm

#endif // KONAN_OHOS
