// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks, coercion-to-kotlin-unit -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: { if (true) work() } as () -> Unit with Unit last expression at runtime
 */

var workCalled = false

fun work() {
    workCalled = true
}

// TESTCASE NUMBER: 1
fun box(): String {
    workCalled = false
    val f: () -> Unit = {
        if (true) work()
    }
    f()
    return if (workCalled) "OK" else "NOK"
}
