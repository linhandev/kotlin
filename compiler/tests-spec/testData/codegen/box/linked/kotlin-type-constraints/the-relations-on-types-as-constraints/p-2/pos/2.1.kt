/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: LUB constraint conversion for conditional expression infers common supertype Any
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val flag = true
    val e: Any = if (flag) 1 else "x"
    return if (e == 1) "OK" else "NOK"
}
