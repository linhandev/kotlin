// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 2
 * DESCRIPTION: THIS_AT token in this@Inner from nested inner class method
 */
// TESTCASE NUMBER: 1

class Container {
    val name = "container"
    inner class Inner {
        val name = "inner"
        inner class Nested {
            fun labels() = this@Container.name + ":" + this@Inner.name
        }
    }
}

fun box(): String {
    return if (Container().Inner().Nested().labels() == "container:inner") "OK" else "NOK"
}
