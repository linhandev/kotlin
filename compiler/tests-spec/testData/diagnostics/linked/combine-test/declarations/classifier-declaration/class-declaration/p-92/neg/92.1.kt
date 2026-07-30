// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 92 -> sentence 92
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 92 -> sentence 92
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 92 -> sentence 92
 * NUMBER: 1
 * DESCRIPTION: in one class, a valid this() secondary coexists with another secondary that has a body but no primary delegation
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(name: String) : this(name, 0)
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor(flag: Boolean)<!> {
        // missing : this(...) while a sibling secondary already delegates correctly
    }
}

// TESTCASE NUMBER: 2
class Account(val id: String) {
    constructor(id: String, tagged: Boolean) : this(id)
    <!PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED!>constructor(code: Int)<!> {}
}
