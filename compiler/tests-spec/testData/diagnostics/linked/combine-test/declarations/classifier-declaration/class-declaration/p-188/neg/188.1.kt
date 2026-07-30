// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 188 -> sentence 188
 * PRIMARY LINKS: inheritance, overriding -> paragraph 188 -> sentence 188
 *                declarations, declaration-visibility -> paragraph 188 -> sentence 188
 *                inheritance, inheriting -> paragraph 188 -> sentence 188
 * NUMBER: 1
 * DESCRIPTION: implementing an interface in a class declaration cannot narrow a public interface member to internal (CANNOT_WEAKEN_ACCESS_PRIVILEGE)
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

class C : I {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override fun f(): Int = 1
}

// TESTCASE NUMBER: 2
interface Named {
    fun name(): String
}

class HiddenName : Named {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override fun name(): String = "x"
}

// TESTCASE NUMBER: 3
interface Labeled {
    val label: String
}

class HiddenLabel : Labeled {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override val label: String = "hidden"
}
