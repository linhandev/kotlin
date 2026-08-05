// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                declarations, function-declaration, extension-function-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: 扩展 operator fun iterator() 使第三方类型可遍历
 */

// TESTCASE NUMBER: 1
class Box(val data: List<Int>)

// TESTCASE NUMBER: 1
operator fun Box.iterator(): Iterator<Int> = data.iterator()

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in Box(listOf(4, 5))) s += x; return s }

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}
