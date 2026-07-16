// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: companion member callable reference resolves via explicit Companion receiver
 */

class Box1161 private constructor() {
    companion object {
        fun only1161(): Int = 7
    }
}

// TESTCASE NUMBER: 1
fun case_1() {
    val ref: () -> Int = Box1161.Companion::only1161
    val ok = ref() == 7
}
