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

#include "KStringExtract.h"
#include <iterator>
#include "utf8.h"

namespace hmm::string {
    std::optional<KInt> Kotlin_String_cachedHashCode(KConstRef thiz) {
        auto header = StringHeader::of(thiz);
        if (header->size() == 0) {
            return 0;
        }
        auto hash = kotlin::std_support::atomic_ref{header->hashCode_}
                .load(std::memory_order_relaxed);
        auto flags = kotlin::std_support::atomic_ref{header->flags_}
                .load(std::memory_order_relaxed);
        if (hash || (flags & StringHeader::HASHCODE_IS_ZERO)) {
            return hash;
        }
        return {};
    }

    template <KStringConversionMode mode>
    std::string to_string_impl(const KChar* it, const KChar* end) noexcept(mode != KStringConversionMode::CHECKED) {
        std::string utf8;
        utf8.reserve(end - it);
        switch (mode) {
            case KStringConversionMode::UNCHECKED:
                utf8::unchecked::utf16to8(it, end, back_inserter(utf8));
                break;
            case KStringConversionMode::CHECKED:
                try {
                    utf8::utf16to8(it, end, back_inserter(utf8));
                } catch (...) {
                    ThrowCharacterCodingException();
                }
                break;
            case KStringConversionMode::REPLACE_INVALID:
                utf8::with_replacement::utf16to8(it, end, back_inserter(utf8));
                break;
        }
        return utf8;
    }

    template <KStringConversionMode>
    std::string to_string_impl(const uint8_t* it, const uint8_t* end) noexcept {
        // Number of high bits in the leading byte of a 2-byte UTF-8 sequence
        // (i.e. the position to right-shift Latin-1 0x80..0xFF code points to
        // produce the leading byte's payload).
        constexpr int kUtf8TwoByteLeadShift = 6;
        std::string result;
        result.resize((end - it) + std::count_if(it, end, [](uint8_t c) { return c & 0x80; }));
        auto out = result.begin();
        while (it != end) {
            auto latin1 = *it++;
            if (latin1 & 0x80) {
                *out++ = 0xC0 | (latin1 >> kUtf8TwoByteLeadShift);
                *out++ = latin1 & 0xBF;
            } else {
                *out++ = latin1;
            }
        }
        return result;
    }

    // Explicit template instantiations
    template std::string to_string_impl<KStringConversionMode::CHECKED>(const KChar*, const KChar*);
    template std::string to_string_impl<KStringConversionMode::UNCHECKED>(const KChar*, const KChar*) noexcept;
    template std::string to_string_impl<KStringConversionMode::REPLACE_INVALID>(const KChar*, const KChar*);

    template std::string to_string_impl<KStringConversionMode::CHECKED>(const uint8_t*, const uint8_t*);
    template std::string to_string_impl<KStringConversionMode::UNCHECKED>(const uint8_t*, const uint8_t*) noexcept;
    template std::string to_string_impl<KStringConversionMode::REPLACE_INVALID>(const uint8_t*, const uint8_t*);
}
