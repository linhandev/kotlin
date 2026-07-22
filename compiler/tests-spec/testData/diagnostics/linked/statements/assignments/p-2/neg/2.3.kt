// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: c1.c += 1 with both plus and plusAssign reports ASSIGN_OPERATOR_AMBIGUITY
 */

class C {
    val c: C = C()
}

operator fun C.plus(a: Any): C = this
operator fun C.plusAssign(a: Any) {}

class C1 {
    var c: C = C()
}

// TESTCASE NUMBER: 1
fun case1() {
    var c1 = C1()
    c1.c <!ASSIGN_OPERATOR_AMBIGUITY!>+=<!> 1
}
