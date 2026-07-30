// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: object can be assigned to an interface-typed reference
 */

// TESTCASE NUMBER: 1
interface Svc {
    fun run(): Int
}

object Engine : Svc {
    override fun run(): Int = 7
}

fun test(s: Svc = Engine): Int = s.run()

fun box(): String {
    if (test() != 7) return "NOK: default"
    if (test(Engine) != 7) return "NOK: explicit"
    val s: Svc = Engine
    if (s.run() != 7) return "NOK: typed"
    return "OK"
}
