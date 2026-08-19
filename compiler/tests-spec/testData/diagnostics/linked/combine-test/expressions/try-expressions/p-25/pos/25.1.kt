// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 *                expressions, elvis-operator-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: finally does not change nullable Elvis result type of try expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: String? = "hi"
    checkSubtype<String>(try {
        x ?: "empty"
    } catch (e: Exception) {
        "error"
    } finally {
        println("done")
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: String? = null
    checkSubtype<String>(try {
        x ?: "empty"
    } catch (e: Exception) {
        "error"
    } finally {
        println("done")
    })
}
