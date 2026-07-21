/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: smart cast after is check avoids explicit cast for String member access
 */
// TESTCASE NUMBER: 1

fun length141(value: Any): Int {
    if (value is String) {
        return value.length
    }
    return 0
}

fun box(): String = if (length141("ok") == 2) "OK" else "NOK"
