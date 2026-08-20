// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 257 -> sentence 257
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 257 -> sentence 257
 *                declarations, function-declaration -> paragraph 257 -> sentence 257
 *                inheritance, inheriting -> paragraph 257 -> sentence 257
 * NUMBER: 1
 * DESCRIPTION: precise types when an interface uses reified type parameters on private inline members exposed via non-inline wrappers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface IdHost {
    private inline fun <reified T> id(x: T): T = x
    fun passInt(x: Int): Int = id(x)
    fun passString(x: String): String = id(x)
}

class IdHostImpl : IdHost

fun case1() {
    val h: IdHost = IdHostImpl()
    h checkType { check<IdHost>() }
    h.passInt(1) checkType { check<Int>() }
    h.passString("a") checkType { check<String>() }
}

// TESTCASE NUMBER: 2
interface TypeChecker {
    private inline fun <reified T> matches(x: Any): Boolean = x is T
    fun isInt(x: Any): Boolean = matches<Int>(x)
    fun isString(x: Any): Boolean = matches<String>(x)
}

class TypeCheckerImpl : TypeChecker

fun case2() {
    val c: TypeChecker = TypeCheckerImpl()
    c.isInt(1) checkType { check<Boolean>() }
    c.isString("s") checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 3
interface TypeNamer {
    private inline fun <reified T> simpleName(): String = T::class.simpleName ?: ""
    fun intName(): String = simpleName<Int>()
    fun stringName(): String = simpleName<String>()
}

class TypeNamerImpl : TypeNamer

fun case3() {
    val n: TypeNamer = TypeNamerImpl()
    n.intName() checkType { check<String>() }
    n.stringName() checkType { check<String>() }
}
