// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 5
 * DESCRIPTION: SUPER_AT token in super@Child.toString from overridden method
 */
// TESTCASE NUMBER: 1

open class Parent {
    override fun toString() = "Parent"
}

class Child : Parent() {
    override fun toString() = "Child-" + super@Child.toString()
}

fun box(): String {
    return if (Child().toString() == "Child-Parent") "OK" else "NOK"
}
