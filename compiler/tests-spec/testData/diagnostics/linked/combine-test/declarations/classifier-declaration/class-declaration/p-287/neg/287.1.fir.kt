// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 287 -> sentence 287
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 287 -> sentence 287
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 287 -> sentence 287
 * NUMBER: 1
 * DESCRIPTION: private companion object members are invisible outside the declaring class (INVISIBLE_REFERENCE); covers function, property, and named companion; contrasts with previous-point inside-class success
 */

// TESTCASE NUMBER: 1
class Host {
    private companion object {
        fun secret(): Int = 1
    }
}

fun case1(): Int = Host.<!INVISIBLE_REFERENCE!>secret<!>()

// TESTCASE NUMBER: 2
class Vault {
    private companion object {
        val key: Int = 7
    }
}

fun case2(): Int = Vault.<!INVISIBLE_REFERENCE!>key<!>

// TESTCASE NUMBER: 3
class Token {
    private companion object Store {
        fun tag(): String = "T"
    }
}

fun case3(): String = Token.<!INVISIBLE_REFERENCE!>tag<!>()
