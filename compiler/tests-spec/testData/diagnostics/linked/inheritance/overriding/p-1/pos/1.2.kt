// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, overriding -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: English540 implements Greeter540.greet; ProtectedOverride540 overrides protected secret()
 */

interface Greeter540 {
    fun greet(): String
}

class English540 : Greeter540 {
    override fun greet(): String = "hello"
}

open class ProtectedBase540 {
    protected open fun secret(): String = "hidden"
}

class ProtectedOverride540 : ProtectedBase540() {
    override fun secret(): String = "visible"

    fun expose(): String = secret()
}

// TESTCASE NUMBER: 1
fun case1(g: English540, p: ProtectedOverride540): String {
    p.expose()
    return g.greet()
}
