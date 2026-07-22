// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks, coercion-to-kotlin-unit -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: consume() with if (true) 42 body coerces Int to Unit in function
 */

var consumeExecuted = 0

fun consume(): Unit {
    if (true) 42.also { consumeExecuted = 1 }
}

// TESTCASE NUMBER: 1
fun box(): String {
    consumeExecuted = 0
    consume()
    return if (consumeExecuted == 1) "OK" else "NOK"
}
