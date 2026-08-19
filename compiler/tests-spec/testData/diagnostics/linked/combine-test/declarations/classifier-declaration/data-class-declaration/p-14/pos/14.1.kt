// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 14 -> sentence 14
 *                declarations, declaration-visibility -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: underscore can skip private primary constructor component inside the data class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Secret(private val code: Int, val label: String) {
    fun exposed(): String {
        val (_, label) = this
        return label
    }
}

fun case_1(s: Secret) {
    checkSubtype<String>(s.exposed())
}
