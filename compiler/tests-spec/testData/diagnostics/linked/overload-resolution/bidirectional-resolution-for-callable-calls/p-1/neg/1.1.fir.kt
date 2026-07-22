// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, bidirectional-resolution-for-callable-calls -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bidirectional resolution fails when referenced member is ambiguous for selected invoke overload
 */

class Host1162N {
    companion object {
        operator fun invoke(x: CharSequence, n: Int = 1): Unit {}
        operator fun invoke(x: String): String = ""
    }

    val x = ""
    fun x(): CharSequence = ""

    // TESTCASE NUMBER: 1
    fun case_1() {
        Companion(::<!NONE_APPLICABLE!>x<!>)
    }
}
