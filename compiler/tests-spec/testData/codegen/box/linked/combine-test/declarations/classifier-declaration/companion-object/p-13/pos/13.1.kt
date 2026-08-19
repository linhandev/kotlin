/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: companion object as interface implementor is usable both via class and as interface-typed value
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Box {
    companion object : I {
        override fun foo() = "impl"
    }
}

fun viaClass(): String = Box.foo()

fun viaInterface(): String {
    val asI: I = Box
    return asI.foo()
}

fun box(): String {
    if (viaClass() != "impl") return "NOK: class"
    if (viaInterface() != "impl") return "NOK: interface"
    if (viaClass() != viaInterface()) return "NOK: mismatch"
    return "OK"
}
