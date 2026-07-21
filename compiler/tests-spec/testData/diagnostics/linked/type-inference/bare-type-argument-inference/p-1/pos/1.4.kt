// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: bare type argument inference — intersection subject T merges inferred arguments from member types
 * HELPERS: checkType
 */

interface Foo144<A>
interface Bar144
class Fee144<T> : Foo144<T>, Bar144

// TESTCASE NUMBER: 1
fun case_1(foo: Foo144<String>) {
    if (foo is Bar144 && foo is Fee144) {
        checkSubtype<Fee144<String>>(foo)
    }
}
