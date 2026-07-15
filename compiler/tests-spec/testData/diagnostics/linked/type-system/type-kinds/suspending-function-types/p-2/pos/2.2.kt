// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, suspending-function-types -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Suspending function types support parameters, receivers, and higher-order usage
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(block: suspend () -> Unit) {
    checkSubtype<suspend () -> Unit>(block)
}


// TESTCASE NUMBER: 2
fun case_2(): suspend (Int) -> String = { it.toString() }


// TESTCASE NUMBER: 3
suspend fun Int.case_3(): String = toString()

fun case_3_use() {
    val ext: suspend Int.() -> String = Int::case_3
    checkSubtype<suspend Int.() -> String>(ext)
}


// TESTCASE NUMBER: 4
fun case_4(f: suspend (String) -> Int) {
    val g: suspend (String) -> Int = f
    checkSubtype<suspend (String) -> Int>(g)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val f: suspend (Int) -> Unit = { }
    val g: (suspend (Int) -> Unit)? = f
    checkSubtype<suspend (Int) -> Unit>(f)
}
