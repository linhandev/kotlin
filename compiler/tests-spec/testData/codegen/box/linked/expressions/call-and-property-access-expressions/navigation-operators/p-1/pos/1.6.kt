// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: bound references b::n and b::double read property and invoke function on receiver
 */

// TESTCASE NUMBER: 1

class Box(val n: Int) {
    fun double(): Int = n * 2
}

fun box(): String {
    val b = Box(3)
    val propRef: () -> Int = b::n
    if (propRef() != 3) return "NOK"
    val funRef: () -> Int = b::double
    if (funRef() != 6) return "NOK"
    return "OK"
}
