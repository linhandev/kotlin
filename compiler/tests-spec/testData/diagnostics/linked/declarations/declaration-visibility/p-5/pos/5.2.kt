// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: protected members remain accessible in indirect subclass through inheritance chain
 */

// TESTCASE NUMBER: 1
open class Root {
    protected fun token(): String = "ok"
}

open class Middle : Root()

class Leaf : Middle() {
    fun readToken(): String = token()
}

// TESTCASE NUMBER: 2
open class RootWithField {
    protected val flag = true
}

open class MiddleWithField : RootWithField()

class GrandChild : MiddleWithField() {
    fun readFlag(): Boolean = flag
}
