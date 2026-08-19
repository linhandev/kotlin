
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: object literal in local scope captures outer variable
 */

// TESTCASE NUMBER: 1
interface Acc {
    fun sum(): Int
}

fun test(): Int {
    var n = 0
    val a = object : Acc {
        override fun sum(): Int {
            n += 1
            return n
        }
    }
    return a.sum() + a.sum()
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
