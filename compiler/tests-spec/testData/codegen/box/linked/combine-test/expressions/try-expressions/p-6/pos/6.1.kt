// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 6 -> sentence 6
 *                expressions, jump-expressions, return-expressions -> paragraph 6 -> sentence 6
 *                type-inference, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: catch with return is Nothing so try expression type remains Int
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): Int {
    val x: Int = try {
        if (flag) 1 else throw Exception()
    } catch (e: Exception) {
        return 2
    }
    return x
}

fun box(): String {
    if (test(true) != 1) return "NOK"
    if (test(false) != 2) return "NOK"
    return "OK"
}
