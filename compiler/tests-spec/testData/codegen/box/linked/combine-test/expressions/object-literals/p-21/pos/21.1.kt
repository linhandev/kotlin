
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: anonymous object type is safely assignable to super interface
 */

// TESTCASE NUMBER: 1
interface P {
    fun id(): String
}

fun test(p: P): String = p.id()

fun run(): String = test(object : P {
    override fun id(): String = "anon"
})

fun box(): String {
    if (run() != "anon") return "NOK"
    return "OK"
}
