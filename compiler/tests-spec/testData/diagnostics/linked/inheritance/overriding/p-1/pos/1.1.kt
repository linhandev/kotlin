// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: OverrideDerived540 f() returns String over Any with implicit protected visibility; PublicOverride540 public override of protected g() is weaker visibility
 */

open class OverrideBase540 {
    protected open fun f(): Any = ""
}

class OverrideDerived540 : OverrideBase540() {
    override fun f(): String = "ok"

    fun useF(): String = f()
}

open class ProtectedVisBase540 {
    protected open fun g(): Int = 1
}

class PublicOverride540 : ProtectedVisBase540() {
    public override fun g(): Int = 2
}

// TESTCASE NUMBER: 1
fun case1(a: PublicOverride540, b: OverrideDerived540): Int {
    b.useF()
    return a.g()
}
