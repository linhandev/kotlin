
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: object literal can be assigned to interface typed variable
 */

// TESTCASE NUMBER: 1
interface Svc {
    fun run(): Int
}

fun test(): Int {
    val s: Svc = object : Svc {
        override fun run(): Int = 5
    }
    return s.run()
}

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}
