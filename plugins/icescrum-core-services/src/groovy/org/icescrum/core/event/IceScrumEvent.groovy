/*
 *
 * This file is part of iceScrum.
 *
 * iceScrum is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * iceScrum is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with iceScrum.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Authors:
 *
 * Vincent Barrier (vincent.barrier@icescrum.com)
 */
package org.icescrum.core.event

import org.springframework.context.ApplicationEvent
import org.icescrum.core.domain.Story
import org.icescrum.core.domain.BacklogElement
import org.icescrum.core.domain.Release
import org.icescrum.core.domain.Sprint
import org.icescrum.core.domain.Product
import org.icescrum.core.domain.Team
import org.icescrum.core.domain.User
import grails.util.GrailsNameUtils
import org.grails.comments.Comment
import grails.plugin.attachmentable.Attachment
import org.icescrum.core.domain.Task
import org.icescrum.core.domain.Feature
import org.icescrum.core.domain.Actor

class IceScrumEvent extends ApplicationEvent {

  static final String EVENT_CREATED = 'Created'
  static final String EVENT_UPDATED = 'Updated'
  static final String EVENT_DELETED = 'Deleted'

  static final EVENT_CUD = [EVENT_CREATED,EVENT_UPDATED]

  Class generatedBy
  def type
  def doneBy

  IceScrumEvent(def source, Class generatedBy, User doneBy, def type){
    super(source)
    this.generatedBy = generatedBy
    this.type = type
    this.doneBy = doneBy
  }

  public getFullType(){
    return GrailsNameUtils.getShortName(this.class)+type
  }
}

class IceScrumStoryEvent extends IceScrumEvent {

  static final String EVENT_SUGGESTED = 'Suggested'
  static final String EVENT_ACCEPTED = 'Accepted'
  static final String EVENT_ESTIMATED = 'Estimated'
  static final String EVENT_PLANNED = 'Planned'
  static final String EVENT_UNPLANNED = 'UnPlanned'
  static final String EVENT_INPROGRESS = 'InProgress'
  static final String EVENT_DONE = 'Done'
  static final String EVENT_UNDONE = 'UnDone'

  static final String EVENT_ACCEPTED_AS_FEATURE = 'AcceptedAsFeature'
  static final String EVENT_ACCEPTED_AS_TASK = 'AcceptedAsTask'

  static final String EVENT_COMMENT_ADDED = 'CommentAdded'
  static final String EVENT_COMMENT_UPDATED = 'CommentUpdated'
  static final String EVENT_COMMENT_DELETED = 'CommentDeleted'

  static final String EVENT_FILE_ATTACHED_ADDED = 'FileAttachedAdded'

  static final EVENT_STATE_LIST = [EVENT_SUGGESTED,EVENT_ACCEPTED,EVENT_ESTIMATED,EVENT_PLANNED,EVENT_UNPLANNED,EVENT_INPROGRESS,EVENT_DONE,EVENT_UNDONE]
  static final EVENT_COMMENT_LIST = [EVENT_COMMENT_ADDED,EVENT_COMMENT_UPDATED]
  static final EVENT_ACCEPTED_AS_LIST = [EVENT_ACCEPTED_AS_FEATURE,EVENT_ACCEPTED_AS_TASK]

  def attachment = null
  def comment = null

  IceScrumStoryEvent(Story story, Class generatedBy, User doneBy, def type){
    super(story, generatedBy, doneBy, type)
  }

  IceScrumStoryEvent(BacklogElement element, Class generatedBy, User doneBy, def type){
    super(element, generatedBy, doneBy, type)
  }

  IceScrumStoryEvent(BacklogElement element, Comment comment, Class generatedBy, User doneBy, def type){
    super(element, generatedBy, doneBy, type)
    this.comment = comment
  }

  IceScrumStoryEvent(BacklogElement element, Attachment attachment, Class generatedBy, User doneBy, def type){
    super(element, generatedBy, doneBy, type)
    this.attachment = attachment
  }
}

class IceScrumSprintEvent extends IceScrumEvent {

  static final String EVENT_ACTIVATED = 'Activated'
  static final String EVENT_CLOSED = 'Closed'
  static final String EVENT_UPDATED_DONE_DEFINITION = 'UpdatedDoneDefinition'
  static final String EVENT_UPDATED_RETROSPECTIVE = 'UpdatedRetrospective'

  IceScrumSprintEvent(Sprint sprint, Class generatedBy, User doneBy, def type){
    super(sprint, generatedBy, doneBy, type)
  }
}

class IceScrumActorEvent extends IceScrumEvent {

  IceScrumActorEvent(Actor actor, Class generatedBy, User doneBy, def type){
    super(actor, generatedBy, doneBy, type)
  }
}

class IceScrumTaskEvent extends IceScrumEvent {

  static final String EVENT_STATE_WAIT = 'Wait'
  static final String EVENT_STATE_IN_PROGRESS = 'InProgress'
  static final String EVENT_STATE_DONE = 'Done'
  static final String EVENT_STATE_BLOCKED = 'Blocked'

  IceScrumTaskEvent(Task task, Class generatedBy, User doneBy, def type){
    super(task, generatedBy, doneBy, type)
  }
}

class IceScrumFeatureEvent extends IceScrumEvent {

  static final String EVENT_COPIED_AS_STORY = 'Copied'
  def story

  IceScrumFeatureEvent(Feature feature, Class generatedBy, User doneBy, def type){
    super(feature, generatedBy, doneBy, type)
  }

  IceScrumFeatureEvent(Feature feature, Story story, Class generatedBy, User doneBy, def type){
    super(feature, generatedBy, doneBy, type)
    this.story = story
  }
}


class IceScrumReleaseEvent extends IceScrumEvent {

  static final String EVENT_ACTIVATED = 'Activated'
  static final String EVENT_CLOSED = 'Closed'
  static final String EVENT_UPDATED_VISION = 'UpdatedVision'

  IceScrumReleaseEvent(Release release, Class generatedBy, User doneBy, def type){
    super(release, generatedBy, doneBy, type)
  }
}

class IceScrumProductEvent extends IceScrumEvent {
  def team = null
  static final String EVENT_TEAM_ADDED = 'TeamAdded'
  static final String EVENT_TEAM_REMOVED = 'TeamRemoved'

  IceScrumProductEvent(Product product, Class generatedBy, User doneBy, def type){
    super(product, generatedBy, doneBy, type)
  }

  IceScrumProductEvent(Product product, Team team, Class generatedBy, User doneBy, def type){
    super(product, generatedBy, doneBy, type)
    this.team = team
  }
}

class IceScrumTeamEvent extends IceScrumEvent {
  def member = null
  static final String EVENT_MEMBER_ADDED = 'MemberAdded'
  static final String EVENT_MEMBER_REMOVED = 'MemberRemoved'

  IceScrumTeamEvent(Team team, Class generatedBy, User doneBy, def type){
    super(team, generatedBy, doneBy, type)
  }

  IceScrumTeamEvent(Team team, User user, Class generatedBy, User doneBy, def type){
    super(team, generatedBy, doneBy, type)
    this.member = user
  }
}

class IceScrumUserEvent extends IceScrumEvent {

  def team = null
  def product = null
  def object = null

  static final String EVENT_IS_PRODUCTOWNER = 'IsProductOwner'
  static final String EVENT_IS_SCRUMMASTER = 'IsScrumMaster'
  static final String EVENT_IS_MEMBER = 'IsMember'
  static final String EVENT_IS_OWNER = 'IsOwner'
  static final String EVENT_NOT_PRODUCTOWNER = 'NotProductOwner'
  static final String EVENT_NOT_SCRUMMASTER = 'NotScrumMaster'
  static final String EVENT_NOT_MEMBER = 'NotMember'

  IceScrumUserEvent(User user, Class generatedBy, User doneBy, def type){
    super(user, generatedBy, doneBy, type)
  }

  IceScrumUserEvent(User user, Team team, Class generatedBy, User doneBy, def type){
    super(user, generatedBy, doneBy, type)
    this.team = team
  }

  IceScrumUserEvent(User user, Product product, Class generatedBy, User doneBy, def type){
    super(user, generatedBy, doneBy, type)
    this.product = product
  }

  IceScrumUserEvent(User user, def object, Class generatedBy, User doneBy, def type){
    super(user, generatedBy, doneBy, type)
    this.object = object
  }
}