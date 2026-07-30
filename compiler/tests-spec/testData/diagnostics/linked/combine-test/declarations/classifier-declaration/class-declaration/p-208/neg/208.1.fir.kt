// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 208 -> sentence 208
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 208 -> sentence 208
 *                inheritance, overriding -> paragraph 208 -> sentence 208
 * NUMBER: 1
 * DESCRIPTION: unqualified super call inside override is ambiguous when a class declaration inherits the same default member from two interfaces (AMBIGUOUS_SUPER); contrasts with p-207 qualified super<IF>
 */

// TESTCASE NUMBER: 1
interface LeftDefault {
    fun f(): Int = 1
}

interface RightDefault {
    fun f(): Int = 2
}

class AmbiguousSuperF : LeftDefault, RightDefault {
    override fun f(): Int = <!AMBIGUOUS_SUPER!>super<!>.f()
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

class AmbiguousSuperTag : LeftTag, RightTag {
    override fun tag(): String = <!AMBIGUOUS_SUPER!>super<!>.tag()
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

class AmbiguousSuperVal : LeftVal, RightVal {
    override val n: Int
        get() = <!AMBIGUOUS_SUPER!>super<!>.n
}
