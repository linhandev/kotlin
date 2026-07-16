// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: no applicable candidate for expected (String) -> T callable reference type
 */

class Box1161N {
    companion object {
        fun foo1161N(): Box1161N = Box1161N()
        fun foo1161N(y: String, x: Any = ""): Box1161N = Box1161N()
    }
}

// TESTCASE NUMBER: 1
fun case_1(): (String) -> Box1161N = Box1161N::<!NONE_APPLICABLE!>foo1161N<!>
