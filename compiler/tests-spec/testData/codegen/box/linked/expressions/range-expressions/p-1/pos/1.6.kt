// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: Wrapper(1)..3 yields MyRange via custom operator rangeTo
 */

// TESTCASE NUMBER: 1

class MyRange(val lo: Int, val hi: Int)

class Wrapper(val start: Int) {
    operator fun rangeTo(rhs: Int): MyRange = MyRange(start, rhs)
}

fun box(): String {
    val r: MyRange = Wrapper(1)..3
    return if (r.lo == 1 && r.hi == 3) "OK" else "NOK"
}
