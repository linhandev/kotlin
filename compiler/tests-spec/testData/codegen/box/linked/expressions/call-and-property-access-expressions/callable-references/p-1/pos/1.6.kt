// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: O::answer invokes object singleton function returning 42
 */

// TESTCASE NUMBER: 1

object O {
    fun answer(): Int = 42
}

fun box(): String {
    val ref: () -> Int = O::answer
    if (ref() != 42) return "NOK"
    return "OK"
}
