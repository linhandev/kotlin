// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: LabelDerived532.label reads inherited open base property
 */

open class LabelBase532 {
    open val label: String get() = "base"
}

class LabelDerived532 : LabelBase532()

// TESTCASE NUMBER: 1
fun case1(d: LabelDerived532): String = d.label
