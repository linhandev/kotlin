// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: h.value reads Holder property via dot member access
 */

// TESTCASE NUMBER: 1

class Holder(val value: Int)

fun box(): String {
    val h = Holder(7)
    return if (h.value == 7) "OK" else "NOK"
}
