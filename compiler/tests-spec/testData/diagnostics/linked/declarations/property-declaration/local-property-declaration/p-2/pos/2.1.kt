// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, local-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: local val and local var declarations inside function body compile successfully
 */

// TESTCASE NUMBER: 1
fun f() {
    val local = 1
    use(local)
}

// TESTCASE NUMBER: 2
fun g() {
    var mutableLocal: String
    mutableLocal = "b"
    use(mutableLocal)
}

fun use(x: Any) {}
