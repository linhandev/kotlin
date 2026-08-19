// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: multiple catch branches participate in overall type inference to Any
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Any>(try {
        1
    } catch (e: IllegalArgumentException) {
        "bad"
    } catch (e: Exception) {
        false
    })
}
