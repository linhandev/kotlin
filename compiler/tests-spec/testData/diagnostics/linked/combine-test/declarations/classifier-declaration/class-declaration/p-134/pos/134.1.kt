// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 134 -> sentence 134
 * NUMBER: 1
 * DESCRIPTION: class whose init block throws still has a constructible Boom type in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Boom {
    init {
        throw IllegalStateException("boom")
    }
}

fun case1() {
    val factory: () -> Boom = ::Boom
    factory checkType { check<() -> Boom>() }
    factory() checkType { check<Boom>() }
}

// TESTCASE NUMBER: 2
class CrashWithLog {
    companion object {
        var seen = false
    }

    init {
        seen = true
        throw IllegalStateException("crash")
    }
}

fun case2() {
    val factory: () -> CrashWithLog = ::CrashWithLog
    factory checkType { check<() -> CrashWithLog>() }
    CrashWithLog.seen checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val boomFactory: () -> Boom = { Boom() }
    val crashFactory: () -> CrashWithLog = { CrashWithLog() }
    boomFactory checkType { check<() -> Boom>() }
    crashFactory checkType { check<() -> CrashWithLog>() }
    boomFactory() checkType { check<Boom>() }
    crashFactory() checkType { check<CrashWithLog>() }
}
