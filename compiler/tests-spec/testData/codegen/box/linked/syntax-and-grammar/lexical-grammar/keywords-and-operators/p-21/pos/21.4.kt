// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 21 -> sentence 21
 * NUMBER: 4
 * DESCRIPTION: COLON token used in class property type annotation class C(val name: String)
 */
// TESTCASE NUMBER: 1

class Person(val name: String)

fun box(): String {
    val person = Person("Alice")
    return if (person.name == "Alice") "OK" else "NOK"
}
