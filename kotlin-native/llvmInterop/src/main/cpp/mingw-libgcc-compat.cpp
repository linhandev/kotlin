/* Copyright (c) 2026 Eazytec Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

extern "C" long long __ashldi3(long long value, int shift) {
    return value << shift;
}

extern "C" long long __ashrdi3(long long value, int shift) {
    return value >> shift;
}

extern "C" int __cmpdi2(long long lhs, long long rhs) {
    return lhs < rhs ? -1 : (lhs > rhs ? 1 : 0);
}

extern "C" long long __divdi3(long long lhs, long long rhs) {
    if (rhs == 0) {
        return 0;
    }
    return lhs / rhs;
}

extern "C" long long __fixdfdi(double value) {
    return static_cast<long long>(value);
}

extern "C" long long __fixsfdi(float value) {
    return static_cast<long long>(value);
}

extern "C" double __floatdidf(long long value) {
    return static_cast<double>(value);
}

extern "C" unsigned long long __lshrdi3(unsigned long long value, int shift) {
    if (shift < 0) {
        return 0;
    }
    return value >> shift;
}

extern "C" long long __moddi3(long long lhs, long long rhs) {
    if (rhs == 0) {
        return 0;
    }
    return lhs % rhs;
}

extern "C" unsigned long long __udivdi3(unsigned long long lhs, unsigned long long rhs) {
    if (rhs == 0) {
        return 0;
    }
    return lhs / rhs;
}

extern "C" unsigned long long __umoddi3(unsigned long long lhs, unsigned long long rhs) {
    if (rhs == 0) {
        return 0;
    }
    return lhs % rhs;
}
