// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 5
 * DESCRIPTION: COLONCOLON token used in class literal Greeter::class
 */
// TESTCASE NUMBER: 1

class Greeter(val name: String)

fun box(): String {
    val clazz = Greeter::class
    return if (clazz.simpleName == "Greeter" && clazz.java.isInstance(Greeter("Alice"))) "OK" else "NOK"
}
