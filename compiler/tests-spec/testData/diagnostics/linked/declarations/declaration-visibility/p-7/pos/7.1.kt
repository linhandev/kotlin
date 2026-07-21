// FIR_IDENTICAL
// LANGUAGE: +ForbidExposingLessVisibleTypesInInline
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: public inline functions may access internal entities marked with PublishedApi
 */

// TESTCASE NUMBER: 1
@PublishedApi
internal fun publishedInternal(): Int = 1

public inline fun publicInline(): Int = publishedInternal()

// TESTCASE NUMBER: 2
class Holder {
    @PublishedApi
    internal fun publishedMethod(): Int = 2
}

public inline fun callPublished(holder: Holder): Int = holder.publishedMethod()
