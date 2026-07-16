// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class delegation forwards interface calls at runtime
 */

// TESTCASE NUMBER: 1
interface Greeter {
    fun greet(): String
}

class GreeterImpl : Greeter {
    override fun greet(): String = "OK"
}

class DelegatingGreeter(g: Greeter) : Greeter by g

fun box(): String {
    val g = DelegatingGreeter(GreeterImpl())
    return if (g.greet() == "OK") "OK" else "NOK"
}
