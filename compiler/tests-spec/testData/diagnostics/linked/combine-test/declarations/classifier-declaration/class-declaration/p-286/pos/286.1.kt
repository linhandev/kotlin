// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 286 -> sentence 286
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 286 -> sentence 286
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 286 -> sentence 286
 * NUMBER: 1
 * DESCRIPTION: precise types when private companion members are used from instance members of the declaring class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Host {
    private companion object {
        fun secret(): Int = 1
    }

    fun ok(): Int = secret()
}

fun case1() {
    val h = Host()
    h checkType { check<Host>() }
    h.ok() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Vault {
    private companion object {
        val key: Int = 7
    }

    fun unlock(): Int = key
}

fun case2() {
    Vault().unlock() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class Token {
    private companion object Store {
        fun tag(): String = "T"
    }

    fun label(): String = tag()
}

fun case3() {
    Token().label() checkType { check<String>() }
}
