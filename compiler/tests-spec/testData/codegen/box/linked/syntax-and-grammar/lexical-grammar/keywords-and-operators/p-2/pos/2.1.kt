// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: DOT token used for simple property access (obj.prop)
 */
// TESTCASE NUMBER: 1

class Person(val name: String, val age: Int)

fun box(): String {
    val person = Person("Alice", 30)
    return if (person.name == "Alice" && person.age == 30) "OK" else "NOK"
}
