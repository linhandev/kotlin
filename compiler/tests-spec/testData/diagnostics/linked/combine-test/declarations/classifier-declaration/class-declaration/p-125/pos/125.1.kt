// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_LATEINIT
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 125 -> sentence 125
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 125 -> sentence 125
 *                declarations, property-declaration, late-initialized-properties -> paragraph 125 -> sentence 125
 * NUMBER: 1
 * DESCRIPTION: lateinit var unread before assignment still has declared type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class BareHost {
    lateinit var name: String

    fun get(): String = name

    fun isNameInitialized(): Boolean = ::name.isInitialized
}

fun case1() {
    val host = BareHost()
    host checkType { check<BareHost>() }
    host.get() checkType { check<String>() }
    host.isNameInitialized() checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 2
class LazyHost {
    lateinit var token: String

    fun read(): String = token

    fun isTokenInitialized(): Boolean = ::token.isInitialized
}

fun case2() {
    val host = LazyHost()
    host checkType { check<LazyHost>() }
    host.read() checkType { check<String>() }
    host.isTokenInitialized() checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val bare = BareHost()
    val lazy = LazyHost()
    bare checkType { check<BareHost>() }
    lazy checkType { check<LazyHost>() }
    bare.isNameInitialized() checkType { check<Boolean>() }
    lazy.isTokenInitialized() checkType { check<Boolean>() }
}
