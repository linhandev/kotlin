// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks, coercion-to-kotlin-unit -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: { if (true) 42 } as () -> Unit coerces Int to Unit at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var executed = 0
    val a: () -> Unit = {
        if (true) 42.also { executed = 1 }
    }
    a()
    return if (executed == 1) "OK" else "NOK"
}
