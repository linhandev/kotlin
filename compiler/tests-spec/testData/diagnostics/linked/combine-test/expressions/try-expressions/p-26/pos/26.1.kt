// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                expressions, elvis-operator-expressions -> paragraph 26 -> sentence 26
 *                expressions, jump-expressions, return-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: finally return overrides try Elvis; try body type still checks
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x: String? = "hi"
    return try {
        checkSubtype<String>(x ?: "empty")
        x ?: "empty"
    } finally {
        return "finally"
    }
}
