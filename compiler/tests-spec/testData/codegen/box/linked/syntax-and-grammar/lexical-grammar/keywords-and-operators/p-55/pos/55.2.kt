// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 2
 * DESCRIPTION: SUPER_AT token in super@Child from inner class calling superclass method
 */
// TESTCASE NUMBER: 1

open class Parent {
    open fun msg() = "parent"
}

class Child : Parent() {
    inner class Helper {
        fun read() = super@Child.msg()
    }
}

fun box(): String {
    return if (Child().Helper().read() == "parent") "OK" else "NOK"
}
