/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: directSinkOk smart casts at direct sink before nested redefinition
 */
// TESTCASE NUMBER: 1

fun directSinkOk1413(): Int {
    var x: Int? = 42
    if (x != null) return x + 1
    run { x = null }
    return 0
}

fun box(): String = if (directSinkOk1413() == 43) "OK" else "NOK"
