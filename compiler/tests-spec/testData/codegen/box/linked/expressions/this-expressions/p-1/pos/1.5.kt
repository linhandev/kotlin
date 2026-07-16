// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: this@lambda refers to labeled lambda implicit receiver
 */

// TESTCASE NUMBER: 1

interface B

object C {
    fun run(block: C.() -> String): String = block()
}

class A {
    val token = "OK"

    fun B.foo(): String {
        return C.run label@{
            if (this@A.token == "OK" && this@label === this) "OK" else "NOK"
        }
    }
}

fun box(): String {
    return object : B {}.let { b ->
        with(A()) { b.foo() }
    }
}
