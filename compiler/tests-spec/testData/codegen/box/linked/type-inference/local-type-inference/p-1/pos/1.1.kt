/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: local property type inferred from initializer drives overload resolution at runtime
 */
// TESTCASE NUMBER: 1

fun consume142(x: Int): Int = x + 1
fun consume142(x: String): Int = x.length

fun localInfer142(): Int {
    val x = 42
    return consume142(x)
}

fun box(): String = if (localInfer142() == 43) "OK" else "NOK"
