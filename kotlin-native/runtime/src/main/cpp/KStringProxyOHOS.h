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

#ifndef RUNTIME_KSTRINGPROXY_OHOS_H
#define RUNTIME_KSTRINGPROXY_OHOS_H

#ifdef KONAN_OHOS
#include <string>
#include <napi/native_api.h>

#include "Common.h"
#include "KString.h"
#include "Memory.h"
#include "Types.h"
#include "TypeInfo.h"
#include "ArkTSStringRef.h"

typedef std::back_insert_iterator<std::string> KStdStringInserter;
typedef KChar* utf8to16(const char*, const char*, KChar*);
typedef KStdStringInserter utf16to8(const KChar*, const KChar*, KStdStringInserter);

namespace hmm {
OBJ_GETTER(Kotlin_ArkTS_CreateStringByProxy, ArkTSStringRef* ref);

ALWAYS_INLINE bool IsKStringProxy(KConstRef string);
ALWAYS_INLINE ArkTSStringRef* KStringProxyGetArkTSStringRef(KConstRef proxy);

ALWAYS_INLINE OBJ_GETTER(unsafeUtf16ToUtf8Impl, KConstRef thizProxy, KInt start, KInt size, utf16to8 conversion);

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_replace, KConstRef thiz, KChar oldChar, KChar newChar);

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_plusStringProxyImpl, KConstRef thizProxy, KConstRef otherProxy);
ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_plusStringImpl, KConstRef thizProxy, KConstRef other);
ALWAYS_INLINE OBJ_GETTER(Kotlin_String_plusStringProxyImpl, KConstRef thiz, KConstRef otherProxy);

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_toCharArray,
                         KConstRef stringProxy,
                         KRef destination,
                         KInt destinationOffset,
                         KInt start,
                         KInt size);

ALWAYS_INLINE OBJ_GETTER(Kotlin_StringProxy_subSequence, KConstRef thizProxy, KInt startIndex, KInt endIndex);

ALWAYS_INLINE KInt Kotlin_StringProxy_compareToStringProxy(KConstRef thizProxy, KConstRef otherProxy);
ALWAYS_INLINE KInt Kotlin_StringProxy_compareToString(KConstRef thizProxy, KConstRef other);
ALWAYS_INLINE KInt Kotlin_String_compareToStringProxy(KConstRef thiz, KConstRef otherProxy);

ALWAYS_INLINE KChar Kotlin_StringProxy_get(KConstRef thizProxy, KInt index);

ALWAYS_INLINE KBoolean Kotlin_StringProxy_equalsWithStringProxy(KConstRef thizProxy, KConstRef otherProxy);
ALWAYS_INLINE KBoolean Kotlin_StringProxy_equalsWithString(KConstRef stringProxy, KConstRef string);

ALWAYS_INLINE KBoolean Kotlin_StringProxy_unsafeRangeEqualsWithStringProxy(KConstRef thizProxy,
                                                                           KInt thizOffset,
                                                                           KConstRef otherProxy,
                                                                           KInt otherOffset,
                                                                           KInt length);
ALWAYS_INLINE KBoolean Kotlin_StringProxy_unsafeRangeEqualsWithString(KConstRef stringProxy,
                                                                      KInt stringProxyOffset,
                                                                      KConstRef string,
                                                                      KInt stringOffset,
                                                                      KInt length);

ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfChar(KConstRef thizProxy, KChar ch, KInt fromIndex);
ALWAYS_INLINE KInt Kotlin_StringProxy_lastIndexOfChar(KConstRef thizProxy, KChar ch, KInt fromIndex);

ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfStringProxy(KConstRef thizProxy, KConstRef otherProxy, KInt fromIndex);
ALWAYS_INLINE KInt Kotlin_StringProxy_indexOfString(KConstRef thizProxy, KConstRef other, KInt fromIndex);
ALWAYS_INLINE KInt Kotlin_String_indexOfStringProxy(KConstRef thiz, KConstRef otherProxy, KInt fromIndex);

ALWAYS_INLINE KInt Kotlin_StringProxy_hashCode(KConstRef thizProxy);

ALWAYS_INLINE KNativePtr Kotlin_StringProxy_getStringAddressOfElement(KConstRef thizProxy, KInt index);
} // namespace hmm
#endif // KONAN_OHOS

#endif // RUNTIME_KSTRINGPROXY_OHOS_H
